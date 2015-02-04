package com.example.address;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Xml;

import com.example.util.LogUtil;
import com.example.util.PublicUtil;

public class SetAddressData {
	private ArrayList<Provinces_Entity> provinces_Entities = new ArrayList<>();
	private ArrayList<City_Entity> city_Entities = new ArrayList<>();

	public void setData(Context context) {
		// Area_Entity area_Entity = new Area_Entity();
		// City_Entity city_Entity = new City_Entity();
		// Provinces_Entity provinces_Entity = new Provinces_Entity();
		//
		// area_Entity.setCity_id(0);
		// area_Entity.setId(0);
		// area_Entity.setName("");
		setProvinces();
		setCity();
		File xmlFile = new File(PublicUtil.DOWNLOAD_APP_PATH, "dizhi.xml"); // data/data/package
		try {
			OutputStream out = new FileOutputStream(xmlFile);
			save(provinces_Entities, city_Entities, out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setProvinces() {
		String countries[] = new String[] { "北京", "天津", "上海", "重庆", "河北", "山西", "内蒙古", "黑龙江", "吉林", "辽宁", "江苏", "浙江",
				"安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海",
				"宁夏", "新疆", "台湾", "香港", "澳门" };
		for (int i = 0; i < countries.length; i++) {
			Provinces_Entity provinces_Entity = new Provinces_Entity();
			provinces_Entity.setId(i);
			provinces_Entity.setName(countries[i]);
			provinces_Entities.add(provinces_Entity);
		}
	}

	private void setCity() {
		final String cities[][] = new String[][] {
				// new String[] { "城市" },
				new String[] { "北京" },
				new String[] { "天津" },
				new String[] { "上海" },
				new String[] { "重庆" },
				new String[] { "石家庄", "张家口", "承德", "唐山", "秦皇岛", "廊坊", "保定", "沧州", "衡水", "邢台", "邯郸" },
				new String[] { "太原", "大同", "朔州", "忻州", "阳泉", "晋中", "吕梁", "长治", "临汾", "晋城", "运城" },
				new String[] { "呼和浩特", "呼伦贝尔", "通辽", "赤峰", "巴彦淖尔", "乌兰察布", "包头", "鄂尔多斯", "乌海" },
				new String[] { "哈尔滨", "黑河", "伊春", "齐齐哈尔", "鹤岗", "佳木斯", "双鸭山", "绥化", "大庆", "七台河", "鸡西", "牡丹江" },
				new String[] { "长春", "白城", "松原", "吉林", "四平", "辽源", "白山", "通化" },
				new String[] { " 沈阳", "铁岭", "阜新", "抚顺", "朝阳", "本溪", "辽阳", "鞍山", "盘锦", "锦州", "葫芦岛", "营口", "丹东", "大连" },
				new String[] { "南京", "连云港", "徐州", "宿迁", "淮安", "盐城", "泰州", "扬州", "镇江", "南通", "常州", "无锡", "苏州" },
				new String[] { "杭州", "湖州", "嘉兴", "绍兴", "舟山", "宁波", "金华", "衢州", "台州", "丽水", "温州" },
				new String[] { "合肥", "淮北", "亳州", "宿州", "蚌埠", "阜阳", "淮南", "滁州", "六安", "马鞍山", "巢湖", "芜湖", "宣城", "铜陵",
						"池州", "安庆", "黄山" },
				new String[] { "福州", "宁德", "南平", "三明", "莆田", "龙岩", "泉州", "漳州", "厦门 " },
				new String[] { "南昌", "九江", "景德镇", "上饶", "鹰潭", "抚州", "新余", "宜春", "萍乡", "吉安", "赣州" },
				new String[] { "济南", "德州", "滨州", "东营", "烟台", "威海", "淄博", "潍坊", "聊城", "泰安", "莱芜", "青岛", "日照", "济宁",
						"菏泽", "临沂", "枣庄" },
				new String[] { "郑州", "安阳", "鹤壁", "濮阳", "新乡", "焦作", "三门峡", "开封", "洛阳", "商丘", "许昌", "平顶山", "周口", "漯河",
						"南阳", "驻马店", "信阳" },
				new String[] { "武汉", "十堰", "襄樊", "随州", "荆门", "孝感", "宜昌", "黄冈", "鄂州", "荆州", "黄石", "咸宁" },
				new String[] { "长沙", "岳阳", "张家界", "常德", "益阳", "湘潭", "株洲", "娄底", "怀化", "邵阳", "衡阳", "永州", "郴州" },
				new String[] { "广州", "韶关", "梅州", "河源", "清远", "潮州", "揭阳", "汕头", "肇庆", "惠州", "佛山", "东莞", "云浮", "汕尾",
						"江门", "中山", "深圳", "珠海", "阳江", "茂名", "湛江" },
				new String[] { "南宁", "桂林", "河池", "贺州", "柳州", "百色", "来宾", "梧州", "贵港", "玉林", "崇左", "钦州", "防城港", "北海" },
				new String[] { "海口", "三亚" },
				new String[] { "成都", "广元", "巴中", "绵阳", "德阳", "达州", "南充", "遂宁", "广安", "资阳", "眉山", "雅安", "内江", "乐山",
						"自贡", "泸州", "宜宾", "攀枝花" }, new String[] { "贵阳", "遵义", "六盘水", "安顺" },
				new String[] { "昆明", "昭通", "丽江", "曲靖", "保山", "玉溪", "临沧", "普洱 " }, new String[] { "拉萨" },
				new String[] { "西安", "榆林", "延安", "铜川", "渭南", "宝鸡", "咸阳", "商洛", "汉中", "安康" },
				new String[] { "兰州", "嘉峪关", "酒泉", "张掖", "金昌", "武威", "白银", "庆阳", "平凉", "定西", "天水", "陇南" },
				new String[] { "西宁" }, new String[] { "银川", "石嘴山", "吴忠", "中卫", "固原" }, new String[] { "乌鲁木齐", "克拉玛依" },
				new String[] { "台北", "高雄 " }, new String[] { "香港" }, new String[] { "澳门" }, };
		int id = 0;
		for (int i = 0; i < cities.length; i++) {
			Provinces_Entity provinces_Entity = provinces_Entities.get(i);
			ArrayList<City_Entity> city_Entities = new ArrayList<>();
			for (int j = 0; j < cities[i].length; j++) {
				City_Entity city_Entity = new City_Entity();
				city_Entity.setProvinces_id(i);
				city_Entity.setId(id);
				city_Entity.setName(cities[i][j]);
				city_Entities.add(city_Entity);
				id++;
			}
			provinces_Entity.setCity_Entities(city_Entities);
		}
	}

	private static final String PROVINCES = "provinces";
	private static final String CITY = "city";
	private static final String PROVINCES_ID = "provinces_id";
	private static final String PROVINCES_NAME = "provinces_name";

	/**
	 * 保存数据到xml文件中
	 * 
	 * @param persons
	 * @param out
	 * @throws Exception
	 */
	public static void save(List<Provinces_Entity> persons, ArrayList<City_Entity> city_Entities, OutputStream out)
			throws Exception {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(out, "UTF-8");
		serializer.startDocument("UTF-8", true);
		serializer.startTag(null, "resources");
		// for (Provinces_Entity person : persons) {
		// serializer.startTag(null, "Provinces");
		// serializer.attribute(null, "id", person.getId() + "");
		// serializer.attribute(null, "name", person.getName().toString());
		// serializer.startTag(null, "name");
		// serializer.attribute(null, "name", "cityName");
		// serializer.text(person.getName().toString());
		// serializer.endTag(null, "name");
		// serializer.endTag(null, "Provinces");
		// }
		for (Provinces_Entity person : persons) {
			serializer.startTag(null, PROVINCES);
			serializer.attribute(null, "id", person.getId() + "");
			serializer.attribute(null, "name", person.getName().toString());
			for (City_Entity city_Entity : person.getCity_Entities()) {
				serializer.startTag(null, CITY);
				serializer.attribute(null, "id", city_Entity.getId() + "");
				serializer.attribute(null, "provinces_id", city_Entity.getProvinces_id() + "");
				serializer.attribute(null, "name", city_Entity.getName().toString());
				serializer.endTag(null, CITY);
			}
			serializer.endTag(null, PROVINCES);
		}
		// for (City_Entity city_Entity : city_Entities) {
		// serializer.startTag(null, CITY);
		// serializer.attribute(null, "id", city_Entity.getId() + "");
		// serializer.attribute(null, "provinces_id",
		// city_Entity.getProvinces_id() + "");
		// serializer.attribute(null, "name", city_Entity.getName().toString());
		// serializer.endTag(null, CITY);
		// }
		serializer.endTag(null, "resources");
		serializer.endDocument();
		out.flush();
		out.close();
	}

	public void getData(Context context) {
		try {
			getWeatherInfo(context.getAssets().open("address.xml"));
			LogUtil.d("address", "provinces_Entities:========================");
			for (int i = 0; i < provinces_Entities.size(); i++) {
				LogUtil.d("address", "name:" + provinces_Entities.get(i).getName());
			}
			LogUtil.d("address", "provinces_Entities:========================");
			LogUtil.d("address", "city_Entities:========================");
			for (int i = 0; i < city_Entities.size(); i++) {
				LogUtil.d("address", "name:" + city_Entities.get(i).getName());
				LogUtil.d("address", "p_id:" + city_Entities.get(i).getProvinces_id() + "");
			}
			LogUtil.d("address", "city_Entities:========================");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getWeatherInfo(InputStream fis) throws Exception {
		/**
		 * DOM解析，加载内存，生成一个树状结构；消耗内存比较大 SAX解析，基于事件的方式，速度快，效率高;不能倒退
		 * pull解析，和SAX解析类似，语法相对方便理解
		 */
		XmlPullParser parser = Xml.newPullParser();

		// 初始化解析器 为utf-8 格式
		parser.setInput(fis, "utf-8");

		// List<Provinces_Entity> provinces_Entities = null;
		Provinces_Entity Info = null;
		City_Entity city_Info = null;
		/**
		 * pull解析的过程： 初始化的时候，定义了一个指针，指向了开头; 需要方法得到该指针 <> 是 一个个的TAG, 中间的是 文本
		 */
		int type = parser.getEventType(); // 得到指针

		// 至上而下解析
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			case XmlPullParser.START_TAG:
				if ("resources".equals(parser.getName())) {
					// 解析到全局开始的标签
					provinces_Entities = new ArrayList<Provinces_Entity>();
				} else if (PROVINCES.equals(parser.getName())) {
					Info = new Provinces_Entity();
					String idStr = parser.getAttributeValue(0);
					String name = parser.getAttributeValue(1);
					Info.setId(Integer.parseInt(idStr)); // 转化接受到的int类型
					Info.setName(name);
					provinces_Entities.add(Info);
				} else if (CITY.equals(parser.getName())) {
					city_Info = new City_Entity();
					String idStr = parser.getAttributeValue(0);
					String name = parser.getAttributeValue(1);
					String provinces_id = parser.getAttributeValue(2);
					city_Info.setId(Integer.parseInt(idStr)); // 转化接受到的int类型
					city_Info.setName(name);
					city_Info.setProvinces_id(Integer.parseInt(provinces_id));
					city_Entities.add(city_Info);
				}
				break;
			case XmlPullParser.END_TAG:
				if ("resources".equals(parser.getName())) {
					// 一个城市的信息 已经处理完毕了
					// provinces_Entities.add(Info); // 将一个城市的信息加载到总信息中
					Info = null;
					city_Info = null;
				}
				break;
			}
			type = parser.next(); // 更改指针的值
		}
		fis.close();
	}
}
