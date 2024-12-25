package com.example.canal.study.common;

import com.alibaba.otter.canal.protocol.CanalEntry.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CanalDataParser {

    private CanalDataParser() {
    }

    /**
     * 元祖类型的对象定义
     *
     * @param <A>
     * @param <B>
     */
    public static class TwoTuple<A, B> {
        public final A eventType;
        public final B columnMap;

        public TwoTuple(A a, B b) {
            eventType = a;
            columnMap = b;
        }
    }

    /**
     * 解析canal中的message对象内容
     */
    public static List<TwoTuple<EventType, Map<String, Object>>> printEntry(List<Entry> entries) {
        List<TwoTuple<EventType, Map<String, Object>>> rows = new ArrayList<>();

        for (Entry entry : entries) {
            // 当前entry（binary log event）的条目类型属于原始数据
            if (entry.getEntryType() == EntryType.ROWDATA) {
                String schemaName = entry.getHeader().getSchemaName();
                String tableName = entry.getHeader().getTableName();
                log.info("database: {}; table: {}", schemaName, tableName);
                RowChange rowChage = null;
                try {
                    // 获取储存的内容
                    rowChage = RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    log.error("parse event has an error , data:{}", entry.getStoreValue(), e);
                }
                // 获取当前内容的事件类型
                assert rowChage != null;
                EventType eventType = rowChage.getEventType();
                // 事件类型是query或数据定义语言DDL直接打印sql语句，跳出继续下一次循环
                if (eventType == EventType.QUERY || rowChage.getIsDdl()) {
                    log.info(" sql ----> {}", rowChage.getSql() + SystemUtils.LINE_SEPARATOR);
                    continue;
                }
                printXAInfo(rowChage.getPropsList());
                // 循环当前内容条目的具体数据
                for (RowData rowData : rowChage.getRowDatasList()) {
                    HashMap<String, Object> map = getStringObjectHashMap(rowData, eventType);
                    rows.add(new TwoTuple<>(eventType, map));
                }
            }
        }
        return rows;
    }

    private static HashMap<String, Object> getStringObjectHashMap(RowData rowData, EventType eventType) {
        List<Column> columns;
        // 事件类型是delete返回删除前的列内容，否则返回改变后列的内容
        if (eventType == EventType.DELETE) {
            columns = rowData.getBeforeColumnsList();
        } else {
            columns = rowData.getAfterColumnsList();
        }
        HashMap<String, Object> map = new HashMap<>(16);
        // 循环把列的name与value放入map中
        for (Column column : columns) {
            map.put(column.getName(), column.getValue());
        }
        return map;
    }

    protected static void printXAInfo(List<Pair> pairs) {
        if (pairs == null) {
            return;
        }

        String xaType = null;
        String xaXid = null;
        for (Pair pair : pairs) {
            String key = pair.getKey();
            if (StringUtils.endsWithIgnoreCase(key, "XA_TYPE")) {
                xaType = pair.getValue();
            } else if (StringUtils.endsWithIgnoreCase(key, "XA_XID")) {
                xaXid = pair.getValue();
            }
        }

        if (xaType != null && xaXid != null) {
            log.info("xaType:{}, xaXid:{}", xaType, xaXid);
        }
    }


}
