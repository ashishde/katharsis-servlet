package com.fox.foxipedia;

import javax.servlet.ServletException;

import io.katharsis.invoker.internal.legacy.KatharsisInvokerBuilder;
import io.katharsis.legacy.locator.SampleJsonServiceLocator;
import io.katharsis.servlet.legacy.AbstractKatharsisServlet;

import javax.servlet.ServletConfig;

/**
 * Servlet implementation class SampleServlet
 */
@SuppressWarnings("deprecation")
public class SampleServlet extends AbstractKatharsisServlet{
	private static final long serialVersionUID = 1L;
	private String resourceSearchPackage;
	private String resourceDefaultDomain;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("In SampleServlet::init()... ");
		super.init(servletConfig);
		resourceSearchPackage = servletConfig.getInitParameter(io.katharsis.core.properties.KatharsisProperties.RESOURCE_SEARCH_PACKAGE);
		resourceDefaultDomain = servletConfig.getInitParameter(io.katharsis.core.properties.KatharsisProperties.RESOURCE_DEFAULT_DOMAIN);
	}


	
	/**
	 * NOTE: A class extending this must provide a platform specific
	 * {@link ˓→JsonServiceLocator} instead of the (testing-purpose)
	 * {@link SampleJsonServiceLocator} below in order to provide advanced
	 * dependency injections for the repositories.
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected KatharsisInvokerBuilder createKatharsisInvokerBuilder() {
		System.out.println("In SampleServlet::createKatharsisInvokerBuilder()... ");
		return new KatharsisInvokerBuilder().resourceSearchPackage(resourceSearchPackage)
				.resourceDefaultDomain(resourceDefaultDomain).jsonServiceLocator(new SampleJsonServiceLocator());
	}
}
