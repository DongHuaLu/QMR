package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_evencut;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_evencut
	 */
	public class Q_evencutContainer {
		
		private var _list:Vector.<Q_evencut> = new Vector.<Q_evencut>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_evencutContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_evencut = new Q_evencut();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_evencut>{
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
