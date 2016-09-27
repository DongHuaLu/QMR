package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_skill_model;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_skill_model
	 */
	public class Q_skill_modelContainer {
		
		private var _list:Vector.<Q_skill_model> = new Vector.<Q_skill_model>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_skill_modelContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_skill_model = new Q_skill_model();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_skillID_q_grade)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_skill_model>{
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
