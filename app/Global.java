import play.GlobalSettings;
import play.api.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;

public class Global extends GlobalSettings{
    /**
     * Get the filters that should be used to handle each request.
     */
    @Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{
                CSRFFilter.class
        };
    }



}