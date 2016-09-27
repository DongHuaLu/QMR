package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_horseweapon;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_horseweapon
	 */
	public class Q_horseweaponContainer {
		
		private var _list:Vector.<Q_horseweapon> = new Vector.<Q_horseweapon>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_horseweaponContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_horseweapon = new Q_horseweapon();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_rank)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_horseweapon>{
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
