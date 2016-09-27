package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_transfer;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_transfer
	 */
	public class Q_transferContainer {
		
		private var _list:Vector.<Q_transfer> = new Vector.<Q_transfer>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_transferContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_transfer = new Q_transfer();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_tran_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_transfer>{
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
