

def roll(num):
	hb = num & 0b11100000
	num <<= 3
	num |= (hb >> 5)
	return num

if __name__ == '__main__':
	for i in range(20, 126):
		f = i * 101
		f = f & 0xFF
		print(f"{i}\t{f}")