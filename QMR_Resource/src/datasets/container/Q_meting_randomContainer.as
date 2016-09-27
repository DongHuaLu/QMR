package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_meting_random;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_meting_random
	 */
	public class Q_meting_randomContainer {
		
		private var _list:Vector.<Q_meting_random> = new Vector.<Q_meting_random>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_meting_randomContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_meting_random = new Q_meting_random();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_metingid)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_meting_random>{
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
