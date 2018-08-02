package com.PrestaShop.DataResources;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.PrestaShop.InitialConfiguration.InitialConfiguration.*;

public class ProductData {
	
	private String nameProduct;
	private String quantityProduct;
	private String priceProduct;

	public void generateNameProduct() {

		nameProduct = "Product " + System.currentTimeMillis();
		
		log.debug("Сгенерированно имя товара: " + nameProduct + ".");
	}

	public void generateQuantityProduct() {

		int quantityProduct = (int) ((Math.random() * 99) + 1);
		this.quantityProduct = quantityProduct + "";
		
		log.debug("Сгенерированно количество товара: " + quantityProduct + ".");
	}

	public void generatePriceProduct() {

		double priceProduct = (Math.random() * 99.9) + 0.1;
		priceProduct = new BigDecimal(priceProduct).setScale(2, RoundingMode.UP).doubleValue();
		this.priceProduct = priceProduct + "";
		
		log.debug("Сгенерированна цена товара: " + priceProduct + ".");
	}
	
	public void generateNamePriceProductQuantity() {
		
		generateNameProduct();
		generateQuantityProduct();
		generatePriceProduct();
	}
	
	public String getNameProduct() {

		return nameProduct;		
	}
	
	public String getQuantityProduct() {
		
		return quantityProduct;	
	}
	
	public String getPriceProduct() {
		
		return priceProduct;
	}

}
