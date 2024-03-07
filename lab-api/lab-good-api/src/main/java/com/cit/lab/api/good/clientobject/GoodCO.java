package com.cit.lab.api.good.clientobject;

import com.cit.basic.dto.BaseCO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class GoodCO extends BaseCO {
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
}
