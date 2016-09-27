package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_scene_monster_area;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_scene_monster_area
	 */
	public class Q_scene_monster_areaContainer {
		
		private var _list:Vector.<Q_scene_monster_area> = new Vector.<Q_scene_monster_area>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_scene_monster_areaContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_scene_monster_area = new Q_scene_monster_area();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_scene_monster_area>{
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
