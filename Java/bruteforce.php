<?
	$u = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.!@#$&";
	$l = strlen($u}
	$trial = 0;
	$result = "";
	$pwd = "";
	
	function main() {
		Console cnsl = System.console();
		$pwd = new String(cnsl.readPassword());
		bruteforcel();
	}
	
	function bruteforcel($a) {
		$pwd = $a;
		$try = "";
		while(strcmp($try, $pwd)) != 0) {
			$try = generate($trial);
			$trial++;
		}
		$result = $try;
	}
	
	function generate($t) {
		$a = "";
		if(t > (l - 1)) {
			$chunk = exp(l, floor(log($t)/log($l)));
			$div = intDiv($t, $chunk);
			$a += $u{$div} + generate($t - $chunk*intDiv($t, $chunk));
		} else {
			$a += $u{$t};
		}
		return $a;
	}
	
	function intDiv($a, $b) {
		return (($a-($a%$b))/$b);
	}
	
	function exp($a, $b) {
		if($b == 1) return $a;
		if($b % 2 == 0) return exp($a*$a, $b/2);
		return $a*exp($a*$a, ($b-1)/2);
	}
}
?>