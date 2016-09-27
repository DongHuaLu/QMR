package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_toplistchest;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_toplistchest
	 */
	public class Q_toplistchestContainer {
		
		private var _list:Vector.<Q_toplistchest> = new Vector.<Q_toplistchest>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_toplistchestContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_toplistchest = new Q_toplistchest();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_chest_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_toplistchest>{
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
