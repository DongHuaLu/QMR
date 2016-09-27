package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_spirittree_pack_con;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_spirittree_pack_con
	 */
	public class Q_spirittree_pack_conContainer {
		
		private var _list:Vector.<Q_spirittree_pack_con> = new Vector.<Q_spirittree_pack_con>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_spirittree_pack_conContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_spirittree_pack_con = new Q_spirittree_pack_con();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_spirittree_pack_con>{
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
