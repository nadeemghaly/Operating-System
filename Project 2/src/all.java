import java.util.Base64.Decoder;

import javax.sound.sampled.ReverbType;

public class all {

	static String[][] registers = new String[32][3];
	static String[][] resvStationsADD;
	static String[][] resvStationsMUL;
	static String[][] resvStationsLW;
	static String[][] instructions;
	static String[] memory = new String[32];
	static String[][] instCycles = new String[6][2];

	static boolean writing = false;

	public static boolean wait = false;
	public static int p = 1;
	public static int pc = 0;
	static boolean done = false;

	public all(int add, int mul, int lw, String[] x, String[] s, String mem[]) {

		for (int i = 0; i < mem.length; i++) {
			memory[i] = mem[i];
		}

		this.instCycles[0][0] = "ADD";
		this.instCycles[0][1] = "" + s[0];

		this.instCycles[1][0] = "SUB";
		this.instCycles[1][1] = "" + s[1];

		this.instCycles[2][0] = "MUL";
		this.instCycles[2][1] = "" + s[2];

		this.instCycles[3][0] = "DIV";
		this.instCycles[3][1] = "" + s[3];

		this.instCycles[4][0] = "LW";
		this.instCycles[4][1] = "" + s[4];

		this.instCycles[5][0] = "SW";
		this.instCycles[5][1] = "" + s[5];

		for (int i = 0; i < registers.length; i++) {
			registers[i][0] = "R" + i;
			registers[i][1] = ".";
			registers[i][2] = "" + i;

		}

		for (int i = 0; i < memory.length; i++) {
			memory[i] = "-1";

		}
		resvStationsADD = new String[add][8];
		resvStationsMUL = new String[mul][8];
		resvStationsLW = new String[lw][8];
		for (int i = 0; i < add; i++) {
			resvStationsADD[i][0] = "A" + (i + 1);
			resvStationsADD[i][1] = "0";
			resvStationsADD[i][2] = ".";
			resvStationsADD[i][3] = ".";
			resvStationsADD[i][4] = ".";
			resvStationsADD[i][5] = ".";
			resvStationsADD[i][6] = ".";
			resvStationsADD[i][7] = "0";

		}

		for (int i = 0; i < mul; i++) {
			resvStationsMUL[i][0] = "M" + (i + 1);
			resvStationsMUL[i][1] = "0";
			resvStationsMUL[i][2] = ".";
			resvStationsMUL[i][3] = ".";
			resvStationsMUL[i][4] = ".";
			resvStationsMUL[i][5] = ".";
			resvStationsMUL[i][6] = ".";
			resvStationsMUL[i][7] = "0";

		}

		for (int i = 0; i < lw; i++) {
			resvStationsLW[i][0] = "L" + (i + 1);
			resvStationsLW[i][1] = "0";
			resvStationsLW[i][2] = ".";
			resvStationsLW[i][3] = ".";
			resvStationsLW[i][4] = ".";
			resvStationsLW[i][5] = ".";
			resvStationsLW[i][6] = ".";
			resvStationsLW[i][7] = "0";

			/*
			 * System.out.println(resvStationsLW[i][0]);
			 * System.out.println(resvStationsLW[i][1]);
			 */
		}

		instructions = new String[x.length][10];
		for (int i = 0; i < x.length; i++) {
			String[] split = x[i].split("\\s+");
			instructions[i][0] = split[0];
			instructions[i][1] = split[1];
			instructions[i][2] = split[2];
			instructions[i][3] = split[3];
			instructions[i][4] = "-1";
			instructions[i][5] = "-1";
			instructions[i][6] = "-1";
			instructions[i][7] = "-1";
			instructions[i][8] = ".";
			instructions[i][9] = "0";

			// instructions[i][4]=i+1 + "";
			/*
			 * System.out.println(instructions[i][0] + " " + instructions[i][1] + " " +
			 * instructions[i][2] + " " + instructions[i][3] + " " + instructions[i][4] );
			 */
		}
	}

	public void decodeInst(String[] x) {
		instructions = new String[x.length][7];
		for (int i = 0; i < x.length; i++) {
			String[] split = x[i].split("\\s+");
			instructions[i][0] = split[0];
			instructions[i][1] = split[1];
			instructions[i][2] = split[2];
			instructions[i][3] = split[3];
			// instructions[i][4]=i+1 + "";

		}

	}

	public static int getCycle(String x) {
		int n = -1;
		for (int i = 0; i < instCycles.length; i++) {
			if (instCycles[i][0].equals(x)) {

				n = Integer.parseInt(instCycles[i][1]);
			} else {

			}

		}
		System.out.println("incorrect type");
		return n;
	}

	public static boolean regUsed(String x) {
		for (int i = 0; i < registers.length; i++) {
			if (registers[i][0].equals(x) && !registers[i][1].equals(".")) {
				return true;
			}
		}
		return false;
	}

	public static int findReg(String x) {
		for (int j = 0; j < registers.length; j++) {
			if (registers[j][0].equals(x)) {
				return j;
			}
		}
		return -1;
	}

	public static int cycles(String x) {
		int n = -1;
		for (int i = 0; i < instCycles.length; i++) {
			if (instCycles[i][0].equals(x)) {
				n = Integer.parseInt(instCycles[i][1]);
			}

		}
		return n;
	}

	public static void replace(String x, String y) {
		for (int i = 0; i < resvStationsADD.length; i++) {
			if (resvStationsADD[i][5].equals(x)) {
				resvStationsADD[i][5] = ".";
				resvStationsADD[i][3] = y;
			}
			if (resvStationsADD[i][6].equals(x)) {
				resvStationsADD[i][6] = ".";
				resvStationsADD[i][4] = y;
			}
			if (resvStationsADD[i][5].equals(".") && resvStationsADD[i][6].equals(".")) {
				resvStationsADD[i][7] = "0";
			}
		}
		for (int i = 0; i < resvStationsMUL.length; i++) {
			if (resvStationsMUL[i][5].contentEquals(x)) {
				resvStationsMUL[i][5] = ".";
				resvStationsMUL[i][3] = y;
			}
			if (resvStationsMUL[i][6].contentEquals(x)) {
				resvStationsMUL[i][6] = ".";
				resvStationsMUL[i][4] = y;
			}
			if (resvStationsMUL[i][5].equals(".") && resvStationsMUL[i][6].equals(".")) {
				resvStationsMUL[i][7] = "0";
			}
		}
		for (int i = 0; i < resvStationsLW.length; i++) {

		}
	}

	public static int findEmptyPlace(String[][] x) {
		for (int i = 0; i < x.length; i++) {
			if (x[i][1].equals("0"))
				return i;

		}
		return -1;
	}

	public static boolean isEmpty(String[][] x) {
		for (int i = 0; i < x.length; i++) {
			if (x[i][1].equals("1"))
				return false;

		}
		return true;
	}

	public static boolean done() {

		for (int i = 0; i < instructions.length; i++) {
			if (instructions[i][8] != ".")
				return false;
		}

		return true;

	}

	public static boolean isReady(String[][] x, String y) {
		for (int i = 0; i < x.length; i++) {
			if (x[i][0].equals(y) && x[i][7].equals("0"))
				return true;
		}
		return false;

	}

	public static int findRes(String[][] x, String y) {
		for (int i = 0; i < x.length; i++) {
			if (x[i][0].equals(y))
				return i;
		}
		return -1;
	}

	public static void start() {
		while (done == false) {

			if (pc < instructions.length) {
				pc++;

			}

			for (int i = 0; i < pc; i++) {

				if ((instructions[i][6].equals("" + (p - 1))
						|| (Integer.parseInt(instructions[i][6]) < (p - 1)) && !instructions[i][6].equals("-1"))) {

					if ((instructions[i][0].equals("MUL") || instructions[i][0].equals("DIV")) && writing == false
							&& !instructions[i][8].equals(".")) {
						int f = findRes(resvStationsMUL, instructions[i][8]);
						switch (instructions[i][0]) {
						case "MUL":
							registers[findReg(instructions[i][1])][2] = ""
									+ (Integer.parseInt(registers[findReg(instructions[i][2])][2])
											* Integer.parseInt(registers[findReg(instructions[i][3])][2]));

							resvStationsMUL[f][1] = "0";
							resvStationsMUL[f][2] = ".";
							resvStationsMUL[f][3] = ".";
							resvStationsMUL[f][4] = ".";
							replace(resvStationsMUL[f][0], instructions[i][1]);
							registers[findReg(instructions[i][1])][1] = ".";
							break;
						case "DIV":
							registers[findReg(instructions[i][1])][2] = ""
									+ (Integer.parseInt(registers[findReg(instructions[i][2])][2])
											/ Integer.parseInt(registers[findReg(instructions[i][3])][2]));
							resvStationsMUL[f][1] = "0";
							resvStationsMUL[f][2] = ".";
							resvStationsMUL[f][3] = ".";
							resvStationsMUL[f][4] = ".";
							replace(resvStationsMUL[f][0], instructions[i][1]);
							registers[findReg(instructions[i][1])][1] = ".";
							break;
						default:
							break;
						}
						instructions[i][7] = "" + p;
						instructions[i][8] = ".";
						writing = true;
						wait = true;

					}

					if ((instructions[i][0].equals("LW") || instructions[i][0].equals("SW")) && writing == false
							&& !instructions[i][8].equals(".")) {
						int f = findRes(resvStationsLW, instructions[i][8]);
						switch (instructions[i][0]) {
						case "LW":
							registers[findReg(instructions[i][1])][2] = ""
									+ memory[(((Integer.parseInt(instructions[i][2]))
											+ Integer.parseInt(registers[findReg(instructions[i][3])][2]))) / 8];

							resvStationsLW[f][1] = "0";
							resvStationsLW[f][2] = ".";
							resvStationsLW[f][3] = ".";
							resvStationsLW[f][4] = ".";
							replace(resvStationsLW[f][0], instructions[i][1]);
							registers[findReg(instructions[i][1])][1] = ".";
							break;
						case "SW":
							memory[(((Integer.parseInt(instructions[i][2]))
									+ Integer.parseInt(registers[findReg(instructions[i][3])][2]))) / 8] = ""
											+ registers[findReg(instructions[i][1])][2];
							resvStationsLW[f][1] = "0";
							resvStationsLW[f][2] = ".";
							resvStationsLW[f][3] = ".";
							resvStationsLW[f][4] = ".";
							replace(resvStationsLW[f][0], instructions[i][1]);
							registers[findReg(instructions[i][1])][1] = ".";
							break;
						default:
							break;
						}
						instructions[i][7] = "" + p;
						instructions[i][8] = ".";
						writing = true;
						wait = true;

					}

					if ((instructions[i][0].equals("ADD") || instructions[i][0].equals("SUB")) && writing == false
							&& !instructions[i][8].equals(".")) {

						int f = findRes(resvStationsADD, instructions[i][8]);
						switch (instructions[i][0]) {
						case "ADD":
							registers[findReg(instructions[i][1])][2] = ""
									+ (Integer.parseInt(registers[findReg(instructions[i][2])][2])
											+ Integer.parseInt(registers[findReg(instructions[i][3])][2]));

							resvStationsADD[f][1] = "0";
							resvStationsADD[f][2] = ".";
							resvStationsADD[f][3] = ".";
							resvStationsADD[f][4] = ".";
							replace(resvStationsADD[f][0], instructions[i][1]);
							registers[findReg(instructions[i][1])][1] = ".";
							break;
						case "SUB":
							registers[findReg(instructions[i][1])][2] = ""
									+ (Integer.parseInt(registers[findReg(instructions[i][2])][2])
											- Integer.parseInt(registers[findReg(instructions[i][3])][2]));
							resvStationsADD[f][1] = "0";
							resvStationsADD[f][2] = ".";
							resvStationsADD[f][3] = ".";
							resvStationsADD[f][4] = ".";
							replace(resvStationsADD[f][0], instructions[i][1]);
							registers[findReg(instructions[i][1])][1] = ".";
							break;
						default:
							break;
						}
						instructions[i][7] = "" + p;
						instructions[i][8] = ".";
						writing = true;

					}

				}

//////////////////////////////////////////////////////ADDING INST IN RESV //////////////////////////////////////////////////////
				if ((instructions[i][0].equals("ADD") || instructions[i][0].equals("SUB"))
						&& instructions[i][9].equals("0")) {

					if (findEmptyPlace(resvStationsADD) != -1) {
						if (p < instructions.length+1) {
							instructions[i][4] = pc + "";
						} else {
							instructions[i][4] = (p + 1) + "";
						}

						instructions[i][9] = "1";
						int c = findEmptyPlace(resvStationsADD);

						resvStationsADD[c][1] = "1";
						resvStationsADD[c][2] = instructions[i][0];
						instructions[i][8] = resvStationsADD[c][0];

//------------- vj or qj --------//
						if (regUsed(instructions[i][2]) == true) {

							int n = findReg(instructions[i][2]);
//System.out.println(registers[n][1]);
							resvStationsADD[c][5] = registers[n][1];
							resvStationsADD[c][7] = "1";
						} else {
							resvStationsADD[c][3] = instructions[i][2];
						}
//------------- vk or qk --------//
						if (regUsed(instructions[i][3]) == true) {
							int n = findReg(instructions[i][3]);
							resvStationsADD[c][6] = registers[n][1];
							resvStationsADD[c][7] = "1";
						} else {
							resvStationsADD[c][4] = instructions[i][3];
						}

						registers[findReg(instructions[i][1])][1] = resvStationsADD[c][0];
					}

				}
				for (int j = 0; j < resvStationsADD.length; j++) {
					if (resvStationsADD[j][5].equals(".") && resvStationsADD[j][6].equals(".")) {
						resvStationsADD[j][7] = "0";
					} else {
						resvStationsADD[j][7] = "1";
					}
				}

				if ((instructions[i][0].equals("MUL") || instructions[i][0].equals("DIV"))
						&& instructions[i][9].equals("0")) {

					if (findEmptyPlace(resvStationsMUL) != -1) {
						if (p < instructions.length+1) {
							instructions[i][4] = pc + "";
						} else {
							instructions[i][4] = (p + 1) + "";
						}
						instructions[i][9] = "1";
						int c = findEmptyPlace(resvStationsMUL);

						resvStationsMUL[c][1] = "1";
						resvStationsMUL[c][2] = instructions[i][0];
						instructions[i][8] = resvStationsMUL[c][0];

//------------- vj or qj --------//
						if (regUsed(instructions[i][2]) == true) {

							int n = findReg(instructions[i][2]);
//System.out.println(registers[n][1]);
							resvStationsMUL[c][5] = registers[n][1];
							resvStationsMUL[c][7] = "1";
						} else {
							resvStationsMUL[c][3] = instructions[i][2];
						}
//------------- vk or qk --------//
						if (regUsed(instructions[i][3]) == true) {
							int n = findReg(instructions[i][3]);
							resvStationsMUL[c][6] = registers[n][1];
							resvStationsMUL[c][7] = "1";
						} else {
							resvStationsMUL[c][4] = instructions[i][3];
						}

						registers[findReg(instructions[i][1])][1] = resvStationsMUL[c][0];
					}

				}

				for (int j = 0; j < resvStationsMUL.length; j++) {
					if (resvStationsMUL[j][5].equals(".") && resvStationsMUL[j][6].equals(".")) {
						resvStationsMUL[j][7] = "0";
					} else {
						resvStationsMUL[j][7] = "1";
					}
				}

				if ((instructions[i][0].equals("LW") || instructions[i][0].equals("SW"))
						&& instructions[i][9].equals("0")) {

					if (findEmptyPlace(resvStationsLW) != -1) {
						if (p < instructions.length+1) {
							instructions[i][4] = pc + "";
						} else {
							instructions[i][4] = (p + 1) + "";
						}
						instructions[i][9] = "1";
						int c = findEmptyPlace(resvStationsLW);

						resvStationsLW[c][1] = "1";
						resvStationsLW[c][2] = instructions[i][0];
						instructions[i][8] = resvStationsLW[c][0];

//------------- vj or qj --------//
						if (regUsed(instructions[i][2]) == true) {

							int n = findReg(instructions[i][2]);
//System.out.println(registers[n][1]);
							resvStationsLW[c][5] = registers[n][1];
							resvStationsLW[c][7] = "1";
						} else {
							resvStationsLW[c][3] = instructions[i][2];
						}
//------------- vk or qk --------//
						if (regUsed(instructions[i][3]) == true) {
							int n = findReg(instructions[i][3]);
							resvStationsLW[c][6] = registers[n][1];
							resvStationsLW[c][7] = "1";
						} else {
							resvStationsLW[c][4] = instructions[i][3];
						}

						registers[findReg(instructions[i][1])][1] = resvStationsLW[c][0];
					}

				}

				for (int j = 0; j < resvStationsLW.length; j++) {
					if (resvStationsLW[j][5].equals(".") && resvStationsLW[j][6].equals(".")) {
						resvStationsLW[j][7] = "0";
					} else {
						resvStationsLW[j][7] = "1";
					}
				}

				if (i != 0) /////////////////////////// SETTING THE START AND FINISH OF THE CYCLES
							/////////////////////////// //////////////////////////////////////////////////////
				{

					if ((instructions[i - 1][0].equals("ADD") || instructions[i - 1][0].equals("SUB"))
							&& instructions[i - 1][9].equals("1") && isReady(resvStationsADD, instructions[i - 1][8])
							&& instructions[i - 1][5].equals("-1")) {

						if (p < instructions.length+1) {
							System.out.println("nononono");
							instructions[i - 1][5] = "" + (pc);
							instructions[i - 1][6] = "" + (pc - 1 + cycles(instructions[i - 1][0]));
						} else {
							System.out.println("lolololololo");
							instructions[i - 1][5] = "" + (p + 1);
							instructions[i - 1][6] = "" + (p + cycles(instructions[i - 1][0]));
						}

					}
					if ((instructions[i - 1][0].equals("MUL") || instructions[i - 1][0].equals("DIV"))
							&& instructions[i - 1][9].equals("1") && isReady(resvStationsMUL, instructions[i - 1][8])
							&& instructions[i - 1][5].equals("-1")) {

						if (p < instructions.length+1) {
							instructions[i - 1][5] = "" + (pc);
							instructions[i - 1][6] = "" + (pc - 1 + cycles(instructions[i - 1][0]));
						} else {
							instructions[i - 1][5] = "" + (p + 1);
							instructions[i - 1][6] = "" + (p + cycles(instructions[i - 1][0]));
						}
					}
					if ((instructions[i - 1][0].equals("LW") || instructions[i - 1][0].equals("SW"))
							&& instructions[i - 1][9].equals("1") && isReady(resvStationsLW, instructions[i - 1][8])
							&& instructions[i - 1][5].equals("-1")) {

						if (p < instructions.length+1) {
							instructions[i - 1][5] = "" + (pc);
							instructions[i - 1][6] = "" + (pc - 1 + cycles(instructions[i - 1][0]));
						} else {
							instructions[i - 1][5] = "" + (p + 1);
							instructions[i - 1][6] = "" + (p + cycles(instructions[i - 1][0]));
						}
					} 

					if (i + 1 == instructions.length) {

						if ((instructions[i][0].equals("ADD") || instructions[i][0].equals("SUB"))
								&& instructions[i][9].equals("1") && isReady(resvStationsADD, instructions[i][8])
								&& instructions[i][5].equals("-1")) {

							if (p < instructions.length+1) {
								System.out.println("nononono");
								instructions[i][5] = "" + (pc);
								instructions[i][6] = "" + (pc - 1 + cycles(instructions[i][0]));
							} else {
								System.out.println("lolololololo");
								instructions[i][5] = "" + (p + 1);
								instructions[i][6] = "" + (p + cycles(instructions[i][0]));
							}

						}
						if ((instructions[i][0].equals("MUL") || instructions[i][0].equals("DIV"))
								&& instructions[i][9].equals("1") && isReady(resvStationsMUL, instructions[i][8])
								&& instructions[i][5].equals("-1")) {

							if (p < instructions.length+1) {
								instructions[i][5] = "" + (pc);
								instructions[i][6] = "" + (pc - 1 + cycles(instructions[i][0]));
							} else {
								instructions[i][5] = "" + (p + 1);
								instructions[i][6] = "" + (p + cycles(instructions[i][0]));
							}
						}
						if ((instructions[i][0].equals("LW") || instructions[i][0].equals("SW"))
								&& instructions[i][9].equals("1") && isReady(resvStationsLW, instructions[i][8])
								&& instructions[i][5].equals("-1")) {

							if (p < instructions.length+1) {
								instructions[i][5] = "" + (pc);
								instructions[i][6] = "" + (pc - 1 + cycles(instructions[i][0]));
							} 
							else {
								instructions[i][5] = "" + (p + 1);
								instructions[i][6] = "" + (p + cycles(instructions[i][0]));
							}
						}

					}
				}

			}
			System.out.println("======================= cycle " + p + "=======================");

			writing = false;
			if (done()) {
				done = true;
			}
			p++;

			System.out.println("            ******** Instructions ********");
			for (int i = 0; i < instructions.length; i++) {
				System.out.println(instructions[i][0] + " " + instructions[i][1] + " " + instructions[i][2] + " "
						+ instructions[i][3] + " " + instructions[i][4] + " " + instructions[i][5] + " "
						+ instructions[i][6] + " " + instructions[i][7] + " " + instructions[i][8] + " "
						+ instructions[i][9]);
			}

			System.out.println("            ******** ADD/SUB REV ********");
			for (int i = 0; i < resvStationsADD.length; i++) {
				System.out.println(resvStationsADD[i][0] + " " + resvStationsADD[i][1] + " " + resvStationsADD[i][2]
						+ " " + resvStationsADD[i][3] + " " + resvStationsADD[i][4] + " " + resvStationsADD[i][5] + " "
						+ resvStationsADD[i][6] + " " + resvStationsADD[i][7]);
			}

			System.out.println("            ******** MUL/DIV REV ********");
			for (int i = 0; i < resvStationsMUL.length; i++) {
				System.out.println(resvStationsMUL[i][0] + " " + resvStationsMUL[i][1] + " " + resvStationsMUL[i][2]
						+ " " + resvStationsMUL[i][3] + " " + resvStationsMUL[i][4] + " " + resvStationsMUL[i][5] + " "
						+ resvStationsMUL[i][6] + " " + resvStationsMUL[i][7]);
			}

			System.out.println("            ******** LW/SW REV ********");
			for (int i = 0; i < resvStationsLW.length; i++) {
				System.out.println(resvStationsLW[i][0] + " " + resvStationsLW[i][1] + " " + resvStationsLW[i][2] + " "
						+ resvStationsLW[i][3] + " " + resvStationsLW[i][4] + " " + resvStationsLW[i][5] + " "
						+ resvStationsLW[i][6] + " " + resvStationsLW[i][7]);
			}

			System.out.println("            ******** Registers ********");
			for (int i = 0; i < registers.length; i++) {
				System.out.println(registers[i][0] + " " + registers[i][1] + " " + registers[i][2]);
			}

			System.out.println("-----------------------------------------------------------");
		}
	}

	public static void main(String[] args) {

		String[] instsCycles = new String[6];

//		instsCycles[0] = "2";
//		instsCycles[1] = "2";
//		instsCycles[2] = "10";
//		instsCycles[3] = "40";
//		instsCycles[4] = "2";
//		instsCycles[5] = "2";

		instsCycles[0] = "4";
		instsCycles[1] = "2";
		instsCycles[2] = "6";
		instsCycles[3] = "40";
		instsCycles[4] = "2";
		instsCycles[5] = "2";

		/*
		 * String[] insts = new String[6]; insts[0] = "MUL R3 R1 R2"; insts[1] =
		 * "ADD R5 R3 R4"; insts[2] = "ADD R7 R2 R6"; insts[3] = "ADD R10 R8 R9";
		 * insts[4] = "MUL R11 R7 R10"; insts[5] = "ADD R5 R5 R11";
		 */
		/*
		 * insts[0] = "LW R6 8 R30"; insts[1] = "LW R2 16 R31"; insts[2] =
		 * "MUL R0 R2 R4"; insts[3] = "SUB R8 R6 R2"; insts[4] = "DIV R10 R0 R6";
		 * insts[5] = "ADD R6 R8 R2";
		 */
		String[] mem = new String[32];
		for (int i = 0; i < mem.length; i++) {
			mem[i] = i + "";
		}
		String[] insts = new String[6];
//		insts[0] = "LW R6 8 R30";
//		insts[1] = "LW R2 16 R31";
//		insts[2] = "MUL R0 R2 R4";
//		insts[3] = "SUB R8 R6 R2";
//		insts[4] = "DIV R10 R0 R6";
//		insts[5] = "ADD R6 R8 R2";

		insts[0] = "MUL R3 R1 R2";
		insts[1] = "ADD R5 R3 R4";
		insts[2] = "ADD R7 R2 R6";
		insts[3] = "ADD R10 R8 R9";
		insts[4] = "MUL R11 R7 R10";
		insts[5] = "ADD R5 R5 R11";

		/* insts[6] = "SUB R12 R13 R14"; */

		all a = new all(3, 2, 3, insts, instsCycles, mem);
		a.start();
	}

}
