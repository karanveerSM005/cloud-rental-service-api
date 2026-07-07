package location;

import java.util.Set;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends javax.ws.rs.core.Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        System.setProperty("jersey.config.server.disableMoxyJson", "true");
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(location.ApiKey.class);
        resources.add(location.DosFilter.class);
        resources.add(location.ItemsResource.class);
        resources.add(location.RequestsResource.class);
        resources.add(location.distanceCalculator.class);
    }
}
