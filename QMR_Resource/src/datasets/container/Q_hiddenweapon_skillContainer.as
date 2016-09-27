package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_hiddenweapon_skill;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_hiddenweapon_skill
	 */
	public class Q_hiddenweapon_skillContainer {
		
		private var _list:Vector.<Q_hiddenweapon_skill> = new Vector.<Q_hiddenweapon_skill>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_hiddenweapon_skillContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_hiddenweapon_skill = new Q_hiddenweapon_skill();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_skill)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_hiddenweapon_skill>{
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
