package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class Product implements Serializable{ /**
	 * 
	 */
	private static final long serialVersionUID = -567117648902464025L;

// Entity 어노테이션 달아서 hibernate에서 DB 테이블을 생성해줌, Table 어노테이션으로 DB테이블명 지정 
	 
	// Id, GeneratedValue 어노테이션으로 Product 클래스의 id 필드가 실제 DB 테이블에서 기본키로 매핑되고
	// GenerationType이 IDENTITY이므로 기본키 값이 DB 테이블에 id 필드가 auto_increment으로 설정되어 자동 생성된다. 
	// Column 어노테이션으로 DB 테이블에서의 필드명을 지정해줄수 있다.
	// 기본키이므로 Column 어노테이션으로 non-null,수정 불가,식별 가능해야함을 설정해주었다. 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id", nullable=false, updatable=false, unique=true)
	private int id;
	
	// 어노테이션으로 데이터 검증(data validator)을 위한 검증 조건(제약 조건)을 설정해 줘야함
	// NotEmpty 어노테이션으로 데이터 null값 검증 
	@NotEmpty(message="The product name must not be empty")
	private String name;
	
	private String category;
	
	// Min 어노테이션으로 데이터 최소값 지정
	@Min(value=0, message="The product price must not be less than zero")
	private int price;
	
	@NotEmpty(message="The product manufacturer must not be empty")
	private String manufacturer;

	@Min(value=0, message="The product unitInStock must not be less than zero")
	private int unitInStock;
	
	private String description;
	
	// 파일 이미지 자체는 DB에 저장을 안 할 것임. 
	// @Transient : DB에 저장하지 않을 필드를 Spring에게 알려주는 어노테이션 
	@Transient
	private MultipartFile productImage;
	
	private String imageFilename;
}
// hibernate = jpa + native
