package com.qsmx.zww.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zww on 2019-03-21.
 */
public class MongoInfo implements Serializable {

        private String _id;
        private double I;
        private double predictionIrrigationWater;
        private double futureWaterRequirement;
        private String waterLevel;
        private String irrigationDate;
        private long b;

        public String get_id() {
                return _id;
        }

        public void set_id(String _id) {
                this._id = _id;
        }

        public double getI() {
                return I;
        }

        public void setI(double i) {
                I = i;
        }

        public double getPredictionIrrigationWater() {
                return predictionIrrigationWater;
        }

        public void setPredictionIrrigationWater(double predictionIrrigationWater) {
                this.predictionIrrigationWater = predictionIrrigationWater;
        }

        public double getFutureWaterRequirement() {
                return futureWaterRequirement;
        }

        public void setFutureWaterRequirement(double futureWaterRequirement) {
                this.futureWaterRequirement = futureWaterRequirement;
        }

        public String getWaterLevel() {
                return waterLevel;
        }

        public void setWaterLevel(String waterLevel) {
                this.waterLevel = waterLevel;
        }

        public String getIrrigationDate() {
                return irrigationDate;
        }

        public void setIrrigationDate(String irrigationDate) {
                this.irrigationDate = irrigationDate;
        }

        public long getB() {
                return b;
        }

        public void setB(long b) {
                this.b = b;
        }

        @Override
        public String toString() {
                return "MongoInfo{" +
                        "_id='" + _id + '\'' +
                        ", I=" + I +
                        ", predictionIrrigationWater=" + predictionIrrigationWater +
                        ", futureWaterRequirement=" + futureWaterRequirement +
                        ", waterLevel='" + waterLevel + '\'' +
                        ", irrigationDate=" + irrigationDate +
                        ", b=" + b +
                        '}';
        }
}
