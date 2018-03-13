package com.ecaray.basicres.entity;

import com.ecar.ecarnetwork.bean.ResBase;

import java.util.List;

/**
 * 类描述:
 * 创建人: Eric_Huang
 * 创建时间: 2016/11/29 18:49
 */
public class CarPlateColorBean extends ResBase {


    private List<CarPlateColor> data;

    public List<CarPlateColor> getCarPlateColorList() {
        return data;
    }

    public class CarPlateColor {
        /**
         * colorName : 蓝色
         * colorValue : 1
         */

        private String colorName;
        private String colorValue;

        public String getColorName() {
            return colorName;
        }

        public String getColorValue() {
            return colorValue;
        }
    }
}
