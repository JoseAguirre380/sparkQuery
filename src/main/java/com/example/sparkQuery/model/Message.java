package com.example.sparkQuery.model;

public class Message implements java.io.Serializable {

        static final long serialVersionUID = 1L;

        private String domain;
        private String trx;
        private String event;
        private String topic;
        private String timestamp;
        private java.util.Map data;
        private String ruleName;
        private String jsonQuery;
        private String translatorMap;

        private Boolean cache;

        private Integer expectedTrxs;

        private String dataSelect;

        private Boolean checkAltaProduccion;

        private Boolean checkCambioUbicacion;

        private Boolean checkCambioEstadoDictamenMaterial;

        public Message() {
        }

        public String getDomain() {
                return this.domain;
        }
        public java.util.Map getData() {
                return this.data;
        }
        public void setData(java.util.Map data) {
                this.data = data;
        }
        public void setDomain(String domain) {
                this.domain = domain;
        }

        public String getEvent() {
                return this.event;
        }

        public void setEvent(String event) {
                this.event = event;
        }

        public String getTimestamp() {
                return this.timestamp;
        }

        public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
        }

        public String getTopic() {
                return this.topic;
        }

        public void setTopic(String topic) {
                this.topic = topic;
        }

        public String getRuleName() {
                return ruleName;
        }

        public void setRuleName(String ruleName) {
                this.ruleName = ruleName;
        }

        @Override
        public String toString() {
                return "Message [domain=" + domain + ", event=" + event
                        + ", timestamp=" + timestamp + ", data=" + data + ", topic="
                        + topic + ", ruleName=" + ruleName + "]";
        }

        public String getTrx() {
                return this.trx;
        }

        public void setTrx(String trx) {
                this.trx = trx;
        }

        public String getJsonQuery() {
                return this.jsonQuery;
        }

        public void setJsonQuery(String jsonQuery) {
                this.jsonQuery = jsonQuery;
        }

        public String getTranslatorMap() {
                return this.translatorMap;
        }

        public void setTranslatorMap(String translatorMap) {
                this.translatorMap = translatorMap;
        }

        public Boolean getCache() {
                return this.cache;
        }

        public void setCache(Boolean cache) {
                this.cache = cache;
        }

        public Integer getExpectedTrxs() {
                return this.expectedTrxs;
        }

        public void setExpectedTrxs(Integer expectedTrxs) {
                this.expectedTrxs = expectedTrxs;
        }

        public String getDataSelect() {
                return this.dataSelect;
        }

        public void setDataSelect(String dataSelect) {
                this.dataSelect = dataSelect;
        }

        public Boolean getCheckAltaProduccion() {
                return this.checkAltaProduccion;
        }

        public void setCheckAltaProduccion(Boolean checkAltaProduccion) {
                this.checkAltaProduccion = checkAltaProduccion;
        }

        public Boolean getCheckCambioUbicacion() {
                return checkCambioUbicacion;
        }

        public void setCheckCambioUbicacion(Boolean checkCambioUbicacion) {
                this.checkCambioUbicacion = checkCambioUbicacion;
        }

        public Boolean getCheckCambioEstadoDictamenMaterial() {
                return checkCambioEstadoDictamenMaterial;
        }

        public void setCheckCambioEstadoDictamenMaterial(Boolean checkCambioEstadoDictamenMaterial) {
                this.checkCambioEstadoDictamenMaterial = checkCambioEstadoDictamenMaterial;
        }

        public Message(String domain, String trx,
                       String event, String topic,
                       String timestamp, java.util.Map data,
                       String ruleName, String jsonQuery,
                       String translatorMap, Boolean cache,
                       Integer expectedTrxs, String dataSelect,
                       Boolean checkAltaProduccion,
                       Boolean checkCambioUbicacion,
                       Boolean checkCambioEstadoDictamenMaterial) {
                this.domain = domain;
                this.trx = trx;
                this.event = event;
                this.topic = topic;
                this.timestamp = timestamp;
                this.data = data;
                this.ruleName = ruleName;
                this.jsonQuery = jsonQuery;
                this.translatorMap = translatorMap;
                this.cache = cache;
                this.expectedTrxs = expectedTrxs;
                this.dataSelect = dataSelect;
                this.checkAltaProduccion = checkAltaProduccion;
                this.checkCambioUbicacion = checkCambioUbicacion;
                this.checkCambioEstadoDictamenMaterial = checkCambioEstadoDictamenMaterial;
                isCambioEstadoDictamenMaterial();
                isCambioUbicacion();
                isAltaProucion();
        }

        public Boolean isAltaProucion() {
                this.checkAltaProduccion = new Boolean(false);
                if (this.data.get("Planta").equals("CHU") && this.data.get("CodMovimiento").equals("136") && this.data.get("EventoDesc").equals("Registrar Produccion")) {
                        this.checkAltaProduccion = new Boolean(true);
                }
                return this.checkAltaProduccion;
        }
        public Boolean isCambioUbicacion() {
                this.checkCambioUbicacion = new Boolean(false);
                if (this.data.get("Planta").equals("CHU")
                        && this.data.get("EventoDesc").equals("Cambio De Ubicacion")) {
                        this.checkCambioUbicacion = new Boolean(true);
                }
                return this.checkCambioUbicacion;
        }
        public Boolean isCambioEstadoDictamenMaterial() {
                this.checkCambioEstadoDictamenMaterial = new Boolean(false);
                if (this.data.get("Planta").equals("CHU") && this.data.get("EventoDesc").equals("Registracion Dictamen")
                        && this.data.get("EventoDesc").equals("Registracion Dictamen")
                        && this.data.get("Defecto").equals("1038")
                        && this.data.get("Desc_Dictamen_Resolucion").equals("Aceptado Con Observaciones")) {
                        this.checkCambioEstadoDictamenMaterial = new Boolean(true);
                }
                return this.checkCambioEstadoDictamenMaterial;
        }

}
