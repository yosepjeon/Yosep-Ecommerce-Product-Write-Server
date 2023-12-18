package com.yosep.product.domain.product.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.yosep.product.domain.annotation.RepositoryTest;
import com.yosep.product.domain.category.entity.ChildCategory;
import com.yosep.product.domain.category.entity.ParentCategory;
import com.yosep.product.domain.common.vo.Money;
import com.yosep.product.domain.product.repository.write.ProductWriteRepository;
import com.yosep.product.domain.product.value.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@RepositoryTest
@ActiveProfiles("test")
class ProductTest {

	@Autowired
	private ProductWriteRepository productWriteRepository;

	@Test
	void 상품엔티티_프로퍼티_비교테스트() {
		String productNameMock = "product1";
		long sellerIdMock = 1;
		Money priceMock = new Money(10000);
		Stock stockMock = new Stock(10, 10);
		ParentCategory parentCategoryMock = ParentCategory.builder()
			.id(1)
			.categoryName("바지")
			.build();
		ChildCategory childCategoryMock = ChildCategory.builder()
			.id(1)
			.parentCategory(parentCategoryMock)
			.categoryName("청바지")
			.build();
		boolean isDeletedMock = false;

		Product product = Product.builder()
			.productName(productNameMock)
			.sellerId(sellerIdMock)
			.price(priceMock)
			.stockQuantity(stockMock)
			.childCategory(childCategoryMock)
			.isDeleted(isDeletedMock)
			.build();

		Product savedProduct = productWriteRepository.save(product);

		assertEquals(product.getProductName(), savedProduct.getProductName());
		assertEquals(product.getPrice(), savedProduct.getPrice());
		assertEquals(product.getSellerId(), savedProduct.getSellerId());
//		assertEquals(product.getParentCategory().getCategoryName(), savedProduct.getParentCategory().getCategoryName());
//		assertEquals(childCategoryMock.getCategoryName(), savedProduct.getChildCategory().getCategoryName());
		assertEquals(product.getStockQuantity().getRemain(), savedProduct.getStockQuantity().getRemain());
	}
}