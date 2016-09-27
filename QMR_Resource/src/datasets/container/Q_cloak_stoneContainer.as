package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_cloak_stone;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_cloak_stone
	 */
	public class Q_cloak_stoneContainer {
		
		private var _list:Vector.<Q_cloak_stone> = new Vector.<Q_cloak_stone>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_cloak_stoneContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_cloak_stone = new Q_cloak_stone();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_item_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_cloak_stone>{
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
