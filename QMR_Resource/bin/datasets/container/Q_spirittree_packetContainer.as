package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_spirittree_packet;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_spirittree_packet
	 */
	public class Q_spirittree_packetContainer {
		
		private var _list:Vector.<Q_spirittree_packet> = new Vector.<Q_spirittree_packet>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_spirittree_packetContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_spirittree_packet = new Q_spirittree_packet();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_packet_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_spirittree_packet>{
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
