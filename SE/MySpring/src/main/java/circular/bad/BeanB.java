package circular.bad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanB {
 
 private final BeanA beanA;
 
 @Autowired
 public BeanB(BeanA beanA) {
  this.beanA = beanA;
 }
 
 @Override
 public String toString() {
  return beanA.getClass().getSimpleName() + " from " + this.getClass().getSimpleName();
 
 }
 
}