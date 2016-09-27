package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_amulet_skill;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_amulet_skill
	 */
	public class Q_amulet_skillContainer {
		
		private var _list:Vector.<Q_amulet_skill> = new Vector.<Q_amulet_skill>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_amulet_skillContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_amulet_skill = new Q_amulet_skill();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_item_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_amulet_skill>{
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
