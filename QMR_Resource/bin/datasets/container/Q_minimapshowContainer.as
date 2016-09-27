package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_minimapshow;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_minimapshow
	 */
	public class Q_minimapshowContainer {
		
		private var _list:Vector.<Q_minimapshow> = new Vector.<Q_minimapshow>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_minimapshowContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_minimapshow = new Q_minimapshow();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_mapid)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_minimapshow>{
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
