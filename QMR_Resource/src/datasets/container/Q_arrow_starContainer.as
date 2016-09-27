package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_arrow_star;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_arrow_star
	 */
	public class Q_arrow_starContainer {
		
		private var _list:Vector.<Q_arrow_star> = new Vector.<Q_arrow_star>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_arrow_starContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_arrow_star = new Q_arrow_star();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_star_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_arrow_star>{
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
