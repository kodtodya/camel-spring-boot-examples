package com.kodtodya.practice.routes;

import com.kodtodya.practice.beans.Training;
import com.kodtodya.practice.services.TrainingService;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainingRestRoute extends RouteBuilder {

	@Autowired
	private TrainingService trainingService;

	@Override
	public void configure() throws Exception {

		onException(CamelExecutionException.class)
				.handled(true)
				.redeliveryDelay(2000)
				.maximumRedeliveries(5)
				// asynchronous redelivery
				.asyncDelayedRedelivery()
				// log the exception details
				.to("log:exceptionLogger");

		//-----------------------------------------------------------------------

		// actual rest service implementation
		rest("/trainings")
				.get("/")
					.produces("application/json")
					.to("direct:fetch-trainings")
				.post("/add")
					.param().name("name")
						.type(RestParamType.query)
						.description("name of the training")
						.endParam()
					.param().name("duration")
						.type(RestParamType.query)
						.description("duration of the training")
						.endParam()
					.param().name("prerequisite")
						.type(RestParamType.query)
						.description("prerequisite of the training")
						.endParam()
					.produces("application/text")
					.to("direct:add-training")
				.delete("/remove")
					.param().name("id").type(RestParamType.query).description("training id to delete").endParam()
					.produces("application/text")
					.to("direct:remove-training")
				.get("/search")
					.param().name("id").type(RestParamType.query).description("training id to search").endParam()
					.produces("application/text")
					.to("direct:search-training")
		;

		// get trainings call
		from("direct:fetch-trainings")
				.log("/trainings request got invoked..")
				.bean(trainingService, "retrieveTrainings")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

		// add call
		from("direct:add-training")
				.log("/trainings/add request got invoked..")
				.bean(this, "createTraining('${headers.name}', '${headers.duration}', '${headers.prerequisite}')")
				.bean(trainingService, "storeTraining")
				.setBody().constant("training added to training-store")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

		// remove call
		from("direct:remove-training")
				.log("/trainings/remove request got invoked..")
				.bean(trainingService, "deleteTraining(${headers.id})")
				.setBody().constant("Training removed from list")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

		// search call
		from("direct:search-training")
				.log("/trainings/search request got invoked..")
				.bean(trainingService, "search(${headers.id})")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
	}

	public Training createTraining(String name, int duration, String prerequisite) {
		return new Training(name, duration, prerequisite);
	}
}