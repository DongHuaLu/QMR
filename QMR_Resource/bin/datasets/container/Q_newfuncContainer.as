package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_newfunc;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_newfunc
	 */
	public class Q_newfuncContainer {
		
		private var _list:Vector.<Q_newfunc> = new Vector.<Q_newfunc>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_newfuncContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_newfunc = new Q_newfunc();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_newfunc>{
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
