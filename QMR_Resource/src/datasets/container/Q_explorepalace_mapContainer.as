package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_explorepalace_map;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_explorepalace_map
	 */
	public class Q_explorepalace_mapContainer {
		
		private var _list:Vector.<Q_explorepalace_map> = new Vector.<Q_explorepalace_map>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_explorepalace_mapContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_explorepalace_map = new Q_explorepalace_map();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_explorepalace_map>{
			return _list;
		}
		
		public function get dict(): Dictionary{
			return _dict;
		}
		
		public function get version(): int{
			return _version;
		}
	}
}
