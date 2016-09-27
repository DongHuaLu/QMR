package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_arrow;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_arrow
	 */
	public class Q_arrowContainer {
		
		private var _list:Vector.<Q_arrow> = new Vector.<Q_arrow>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_arrowContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_arrow = new Q_arrow();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_arrow_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_arrow>{
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
