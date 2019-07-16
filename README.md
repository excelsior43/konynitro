# Lightweight framework to develop kony java services

I have written a simple framework on top of 'kony java service' framework to make it easy to use java services.
please refer https://www.kony.com/ to know about kony java services developed for kony mobile fabric 8.4


USAGE: 

```java
public class SomeMyKonyService extends NitroService
{
   private final static Logger logger = Logger.getLogger(SomeMyKonyService.class);
 

   @NitroServiceMethod("OperationIDOfMyServiceDefinedInMobileFabric")
   public Result myService(
      @NitroParameter("param1") String someParam1,
      @NitroParameter("param2") String someParam2,
      ) 
      throws Throwable
     {
      Result result = new Result();
        /**
        ** process and load result with some business logic
        **/
        return result
     }
   }
   '''
 
