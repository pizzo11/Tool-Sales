package com.example.demo2;

//Tool Class
public class Tool {
        private String ToolCode;
        private String ToolType;
        private String ToolBrand;

        public Tool(String ToolCode, String ToolType, String ToolBrand) {
            this.ToolCode = ToolCode;
            this.ToolType = ToolType;
            this.ToolBrand = ToolBrand;
        }

        public String getToolCode() {
            return ToolCode;
        }

        public String getToolType() {
            return ToolType;
        }

        public String getToolBrand() {
            return ToolBrand;
        }

        @Override
        public String toString() {
            return ToolCode;
        }
}

