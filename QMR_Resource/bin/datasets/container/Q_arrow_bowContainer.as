package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_arrow_bow;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_arrow_bow
	 */
	public class Q_arrow_bowContainer {
		
		private var _list:Vector.<Q_arrow_bow> = new Vector.<Q_arrow_bow>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_arrow_bowContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_arrow_bow = new Q_arrow_bow();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_bow_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_arrow_bow>{
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
