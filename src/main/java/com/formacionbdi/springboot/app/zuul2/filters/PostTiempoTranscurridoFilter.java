package com.formacionbdi.springboot.app.zuul2.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter{
	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();// para cachar la peticion
		HttpServletRequest request = ctx.getRequest();
		log.info("Entradno a post");
		
		Long tiempoinicio =  (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long timpoTranscurrido = tiempoFinal -tiempoinicio;
		log.info(String.format("Tiempo transcurrdido en segundos %s", timpoTranscurrido.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrdido en milisegundos %s", timpoTranscurrido));
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
