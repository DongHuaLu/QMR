package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_collect;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_collect
	 */
	public class Q_collectContainer {
		
		private var _list:Vector.<Q_collect> = new Vector.<Q_collect>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_collectContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_collect = new Q_collect();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_coll_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_collect>{
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
