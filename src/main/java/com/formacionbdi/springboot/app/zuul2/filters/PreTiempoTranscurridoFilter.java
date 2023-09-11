package com.formacionbdi.springboot.app.zuul2.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {//para ejecutat si o no el filtr
		//si existen datos en el request 
		return true;
	}

	@Override
	public Object run() throws ZuulException { //logica para cachar el tiempo antews de que se resuelva la ruta
		
		RequestContext ctx = RequestContext.getCurrentContext();// para cachar la peticion
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		Long tiempoinicio =  System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoinicio);
		return null;
	}

	@Override
	public String filterType() { //tipos de filtros pre, post route
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
