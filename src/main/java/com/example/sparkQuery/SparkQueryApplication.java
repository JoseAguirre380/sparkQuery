package com.example.sparkQuery;

import com.example.sparkQuery.spark.SparkExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SparkQueryApplication {

	public static void main(String[] args) {

		SpringApplication.run(SparkQueryApplication.class, args);
		SparkExecutor sparkExecutor = new SparkExecutor();
		sparkExecutor.executeQuery("{\n" +
				"   \"domain\": \"Produccion\",\n" +
				"   \"trx\": \"TrazabilidadMateriales\",\n" +
				"   \"timestamp\": \"1574386120\",\n" +
				"   \"data\": {\n" +
				"           \"Nro_Bulto\": \"3B011922CM300\",\n" +
				"           \"Fecha\": \"20191002\",\n" +
				"           \"Planta\": \"CHU\",\n" +
				"           \"Linea\": \"MC3_CHU\",\n" +
				"           \"Linea_ori\": \"TR_CHU_UNI\",\n" +
				"           \"Peso\": \"16850\",\n" +
				"           \"CodigoProducto\": \"MP518485\",\n" +
				"           \"Defecto\": \"NA\",\n" +
				"           \"CodMovimiento\": \"136\",\n" +
				"           \"EstadoMaterial\": \"MEPE\",\n" +
				"           \"OFA\": \"801384848003002000000\",\n" +
				"           \"Fecha_movimiento\": \"02/10/2019  12:44:23 p. m.\",\n" +
				"           \"EventoDesc\": \"Registrar Produccion\",\n" +
				"           \"Estrategia\": \"NA\",\n" +
				"           \"Almacen\": \"CHURUN\",\n" +
				"           \"Desc_Dictamen_Resolucion\": \"Aceptado Con Observaciones\",\n" +
				"           \"Dictamen\": \"Aceptado\",\n" +
				"           \"EstadoLaboratorio\": \"BLOQ\"\n" +
				"   }\n" +
				"}");
	}

}
