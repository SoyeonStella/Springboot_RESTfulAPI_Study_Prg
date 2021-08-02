package kr.ac.hansung.cse.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Product;

// 스프링에서 스캔하다가(단, 어느 패키지를 스캔할지를 지정해 줘야한다.) 
// @Component(여기서는 더 명확히  @Repository 사용함)가 있으면 해당되 class를 바탕으로 하여 빈으로 등록해준다.
// @Component() 안의 문자열은 빈에 대한 id이다. 
// bean으로 등록해주면 스프링 컨테이너에 의해 라이프사이클을 관리해줄수있다.
// 이 어노테이션 꼭 기억하기 매우중요  
// service -> @Service, controller -> @Controller
// dao -> @Repository 임!!!
// DAO는 스프링에서 제공하는 JDBC Template을 사용한다. 
// 따라서 JDBC Template 객체를 생성해줘야 한다. 
// 또한 JDBC Template에 DataSource를 주입해주는 부분도 필요하다. 
// 그러나 Hibernate와 Spring 통합 개발 환경에서는 JDBC Template에 대한 아래와 같은 부분이 필요없다. 
/*
 	// JDBC Template을 활용함
	private JdbcTemplate jdbcTemplate;

	@Autowired
	// dataSource가 싱글톤이므로 자료형으로 알아서 찾아서 주입해준다.
	// setter method. 이 메서드 호출시 DataSource를 의존성 주입을 통해서 주입해 준다.
	public void setDataSource(DataSource dataSource) {

		// DataSource를 활용해 jdbcTemplate 인스턴스 생성
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
 
 * */
// 또한, CRUD 작업을 수행하는 메서드들에서 JDBC Template과 모든 sql문을 사용하지 않는다. 
// Transactional 어노테이션으로 transaction 관련 메서드들(begin(),commit() 등)이
// AOP기능을 사용해 자동으로 모든 CRUD 메서드들에 삽입된다. 
@Repository
@Transactional
public class ProductDao {

	// SessionFactory Bean을 의존성 주입으로 받아온다.
	@Autowired
	private SessionFactory sessionFactory;

	// DB에서 제품 리스트를 가져와주는 메서드
	public List<Product> getProducts() {
		
		Session session = sessionFactory.getCurrentSession();
		// session이 가지고 있는 메서드들로 가져올 수 없어서 hql 쿼리문을 만들어 가져온다 
		String hql = "from Product";

		Query<Product> query = session.createQuery(hql, Product.class);
		List<Product> productList = query.getResultList();

		return productList;

	}

	// DB에서 특정 id에 해당하는 제품을 가져다주는 메서드
	public Product getProductById(int id) {
		// SessionFactory 객체를 받아옴
		Session session = sessionFactory.getCurrentSession();
		// 인자로 받아온 id 값을 바탕으로 해서 Product.class와 매핑되는 테이블에서 id에 해당하는 레코드를 가져옴
		Product product = (Product) session.get(Product.class, id);

		return product;

	}

	// DB에 제품을 추가해주는 메서드
	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();
        
	}

	// 특정 id에 해당하는 제품을 DB에서 삭제하는 메서드
	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
        session.delete(product);
        session.flush();

	}

	// DB에 제품 내용 수정 사항을 반영해주는 메서드
	public void updateProduct(Product product) {
		
		Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();

	}
}
