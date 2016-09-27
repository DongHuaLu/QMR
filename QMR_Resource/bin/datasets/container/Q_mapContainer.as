package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_map;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_map
	 */
	public class Q_mapContainer {
		
		private var _list:Vector.<Q_map> = new Vector.<Q_map>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_mapContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_map = new Q_map();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_map_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_map>{
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
