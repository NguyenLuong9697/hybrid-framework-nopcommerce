package serverConfig;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.Sources;

//classpath: D:\Automation FC\07-Hybrid Framework Nopcommerce\bin
@Sources({"classpath:${server}.properties"})
public interface IServer extends Config{
	
	@Key("url")
	String url();
	
	@Key("email")
	String email();
	
	@Key("pass")
	String pass();
}
