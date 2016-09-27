package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_hiddenweapon;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_hiddenweapon
	 */
	public class Q_hiddenweaponContainer {
		
		private var _list:Vector.<Q_hiddenweapon> = new Vector.<Q_hiddenweapon>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_hiddenweaponContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_hiddenweapon = new Q_hiddenweapon();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_rank)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_hiddenweapon>{
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
