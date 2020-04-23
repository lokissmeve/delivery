
package warehousepf.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by uengine on 2018. 11. 21..
 */

@FeignClient(name="stockRequest", url="http://:8082")
public interface WarehouseService {

    @RequestMapping(method= RequestMethod.GET, path="/warehouses")
    public void stockRequest(@RequestBody Warehouse warehouse);

}