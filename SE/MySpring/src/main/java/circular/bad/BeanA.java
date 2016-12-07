package circular.bad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanA {
 
 private final BeanB beanB;
 
 @Autowired
 public BeanA(BeanB beanB) {
  this.beanB = beanB;
 }
 
 @Override
 public String toString() {
  return beanB.getClass().getSimpleName() + " from " + this.getClass().getSimpleName();
 }
 
}
 
