package warehousepf;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Delivery_table")
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String product;
    private Integer qty;

    @PostPersist
    public void onPostPersist(){
        Delivered delivered = new Delivered();
        BeanUtils.copyProperties(this, delivered);
        delivered.publish();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

//        warehousepf.external.Warehouse warehouse = new warehousepf.external.Warehouse();
//        // mappings goes here
//        Application.applicationContext.getBean(warehousepf.external.WarehouseService.class)
//            .stockRequest(warehouse);

//        OrderPlaced orderPlaced = new OrderPlaced();
//        orderPlaced.setId(this.getId());
//        orderPlaced.setConsumerName(this.getConsumerName());
//        orderPlaced.setProductId(this.getProductId());
//        orderPlaced.setProductName(this.getProductName());
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = null;
//
//        try {
//            json = objectMapper.writeValueAsString(orderPlaced);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("JSON format exception", e);
//        }
//        System.out.println(json);
//
//        Processor processor = DemoApplication.applicationContext.getBean(Processor.class);
//        MessageChannel outputChannel = processor.output();
//
//        outputChannel.send(MessageBuilder
//                .withPayload(json)
//                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
//                .build());


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

}
