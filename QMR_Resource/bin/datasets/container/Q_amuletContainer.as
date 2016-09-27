package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_amulet;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_amulet
	 */
	public class Q_amuletContainer {
		
		private var _list:Vector.<Q_amulet> = new Vector.<Q_amulet>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_amuletContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_amulet = new Q_amulet();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_model_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_amulet>{
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
