package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_rank;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_rank
	 */
	public class Q_rankContainer {
		
		private var _list:Vector.<Q_rank> = new Vector.<Q_rank>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_rankContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_rank = new Q_rank();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_ranklv)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_rank>{
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
