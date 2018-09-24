package springboot.entities;

import org.springframework.beans.factory.annotation.Value;

public interface NamesOnlyValueAnnotation {

	@Value("#{ target.firstName + ' ' + target.lastName }")
	String getFullName();

}
