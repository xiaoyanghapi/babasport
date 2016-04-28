package cn.itcast.core.service.product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Img;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.dao.product.ProductDao;
import cn.itcast.core.query.product.ImgQuery;
import cn.itcast.core.query.product.ProductQuery;
/**
 * 商品事务层
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductDao productDao;
	//为了加分布式缓存所以用加载service
	@Resource
	private ImgService imgService;
	@Resource
	private SkuService skuService;
	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addProduct(Product product) {
//		生成商品编号
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String no = df.format(new Date());
		product.setNo(no);
		product.setCreateTime(new Date());
		
//		保存顺序->1商品  2照片 3sku
//		返回商品id
//		商品保存
		Integer i = productDao.addProduct(product);
		/**
		 * 保存图片
		 */
		Img  img = product.getImg();
		img.setIsDef(1);
		img.setProductId(product.getId());
		Integer addImg = imgService.addImg(img);
		System.out.println(addImg);
		/**
		 * 保存sku 最小销售单元 
		 */
//		实例化最小销售单元
		Sku sku  = new Sku();
		sku.setProductId(product.getId());
		sku.setDeliveFee(10.00);
//		售价
		sku.setSkuPrice(0.00);
//		市场价
		sku.setMarketPrice(0.00);
//		库存
		sku.setStockInventory(0);
//		购买限制
		sku.setSkuUpperLimit(0);
//		添加时间
		sku.setCreateTime(new Date());
//		是否是最新
		sku.setLastStatus(1);
//		是否是赠品
		sku.setSkuType(1);
//		销量
		sku.setSales(0);
		
		for(String color :product.getColor().split(",")){
			//放入颜色id
			sku.setColorId(Integer.parseInt(color));
			for(String size:product.getSize().split(",")){
				sku.setSize(size);
				skuService.addSku(sku);
			}
			
		}
		
		 return i;
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Product getProductByKey(Integer id) {
		return productDao.getProductByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Product> getProductsByKeys(List<Integer> idList) {
		return productDao.getProductsByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return productDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return productDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateProductByKey(Product product) {
		return productDao.updateProductByKey(product);
	}
	
	@Transactional(readOnly = true)
	public Pagination getProductListWithPage(ProductQuery productQuery) {
		Pagination p = new Pagination(productQuery.getPageNo(),productQuery.getPageSize(),productDao.getProductListCount(productQuery));
		List<Product> products = productDao.getProductListWithPage(productQuery);
		for (Product product : products) {
			ImgQuery imgQuery = new ImgQuery();
			imgQuery.setProductId(product.getId());
			imgQuery.setIsDef(1);
			product.setImg(imgService.getImgList(imgQuery).get(0));
		}
		
		p.setList(products);
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Product> getProductList(ProductQuery productQuery) {
		return productDao.getProductList(productQuery);
	}
}
