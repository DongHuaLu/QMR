package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_public_medalofhonor;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_public_medalofhonor
	 */
	public class Q_public_medalofhonorContainer {
		
		private var _list:Vector.<Q_public_medalofhonor> = new Vector.<Q_public_medalofhonor>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_public_medalofhonorContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_public_medalofhonor = new Q_public_medalofhonor();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_public_medalofhonor>{
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
