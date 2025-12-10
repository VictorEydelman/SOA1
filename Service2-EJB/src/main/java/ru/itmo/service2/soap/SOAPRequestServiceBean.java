package ru.itmo.service2.soap;

import jakarta.ejb.Stateless;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Service;
import ru.itmo.service2.domain.Route;
import ru.itmo.service2.domain.RouteList;
import ru.itmo.service2.ejb.RequestService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;

@Stateless
public class SOAPRequestServiceBean implements RequestService {

    private static final String SERVICE1_WSDL_URL = "http://localhost:8081/WebService1?wsdl";
    private static final String NAMESPACE_URI = "http://soap.service1.itmo.ru/";
    private static final String SERVICE_NAME = "WebService1";
    private static final String PORT_NAME = "WebService1Port";

    private WebService1 getPort() {
        try {
            URL wsdlUrl = new URL(SERVICE1_WSDL_URL);
            QName serviceQName = new QName(NAMESPACE_URI, SERVICE_NAME);
            QName portQName = new QName(NAMESPACE_URI, PORT_NAME);

            Service service = Service.create(wsdlUrl, serviceQName);
            WebService1 port = service.getPort(portQName, WebService1.class);

            BindingProvider bp = (BindingProvider) port;
            Map<String, Object> requestContext = bp.getRequestContext();

            return port;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SOAP client for Service1", e);
        }
    }

    @Override
    public RouteList getRoutes(int page, int size, String sort) {
        return getPort().getAllRoutes(page, size, sort);
    }

    @Override
    public Route postRoute(Route route) {
        return getPort().createRoute(route);
    }

}
