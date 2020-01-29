/* as2jTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. as2jTokenManager.java */
package jason.asSyntax.parser;
import java.util.*;
import java.io.*;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jason.*;
import jason.asSemantics.*;
import jason.bb.*;
import jason.asSyntax.*;
import jason.asSyntax.directives.*;
import jason.asSyntax.ArithExpr.ArithmeticOp;
import jason.asSyntax.LogExpr.LogicalOp;
import jason.asSyntax.RelExpr.RelationalOp;
import jason.asSyntax.PlanBody.BodyType;
import jason.asSyntax.Trigger.TEOperator;
import jason.asSyntax.Trigger.TEType;
import jason.util.*;

/** Token Manager. */
@SuppressWarnings("unused")public class as2jTokenManager implements as2jConstants {

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1){
   switch (pos)
   {
      case 0:
         if ((active1 & 0x1L) != 0L)
            return 15;
         if ((active0 & 0x2000000000L) != 0L)
            return 48;
         if ((active0 & 0x3ef700L) != 0L)
         {
            jjmatchedKind = 26;
            return 49;
         }
         return -1;
      case 1:
         if ((active0 & 0x20000L) != 0L)
            return 49;
         if ((active0 & 0x3cf700L) != 0L)
         {
            jjmatchedKind = 26;
            jjmatchedPos = 1;
            return 49;
         }
         return -1;
      case 2:
         if ((active0 & 0x10b400L) != 0L)
            return 49;
         if ((active0 & 0x2c4300L) != 0L)
         {
            jjmatchedKind = 26;
            jjmatchedPos = 2;
            return 49;
         }
         return -1;
      case 3:
         if ((active0 & 0xc0100L) != 0L)
            return 49;
         if ((active0 & 0x204200L) != 0L)
         {
            jjmatchedKind = 26;
            jjmatchedPos = 3;
            return 49;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 38;
         return jjMoveStringLiteralDfa1_0(0x1000000000000L, 0x0L);
      case 38:
         return jjStopAtPos(0, 56);
      case 40:
         return jjStopAtPos(0, 46);
      case 41:
         return jjStopAtPos(0, 47);
      case 42:
         jjmatchedKind = 63;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x2L);
      case 43:
         return jjStopAtPos(0, 41);
      case 44:
         return jjStopAtPos(0, 52);
      case 45:
         return jjStopAtPos(0, 42);
      case 46:
         return jjStartNfaWithStates_0(0, 37, 48);
      case 47:
         return jjStartNfaWithStates_0(0, 64, 15);
      case 58:
         jjmatchedKind = 39;
         return jjMoveStringLiteralDfa1_0(0x8001000000000L, 0x0L);
      case 59:
         return jjStopAtPos(0, 45);
      case 60:
         jjmatchedKind = 49;
         return jjMoveStringLiteralDfa1_0(0x200010000000000L, 0x0L);
      case 61:
         jjmatchedKind = 61;
         return jjMoveStringLiteralDfa1_0(0x4800000000000000L, 0x0L);
      case 62:
         jjmatchedKind = 50;
         return jjMoveStringLiteralDfa1_0(0x400000000000000L, 0x0L);
      case 63:
         return jjStopAtPos(0, 44);
      case 64:
         return jjStopAtPos(0, 16);
      case 91:
         return jjStopAtPos(0, 53);
      case 92:
         return jjMoveStringLiteralDfa1_0(0x1000000000000000L, 0x0L);
      case 93:
         return jjStopAtPos(0, 55);
      case 94:
         return jjStopAtPos(0, 43);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x4000L, 0x0L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x1000L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0xc8000L, 0x0L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x100200L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x20000L, 0x0L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x2000L, 0x0L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x400L, 0x0L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x100L, 0x0L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x200000L, 0x0L);
      case 123:
         return jjStopAtPos(0, 34);
      case 124:
         jjmatchedKind = 54;
         return jjMoveStringLiteralDfa1_0(0xc00000L, 0x0L);
      case 125:
         return jjStopAtPos(0, 35);
      case 126:
         return jjStopAtPos(0, 11);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0, long active1){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 33:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 48);
         break;
      case 38:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000L, active1, 0L);
      case 42:
         if ((active1 & 0x2L) != 0L)
            return jjStopAtPos(1, 65);
         break;
      case 45:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(1, 36);
         else if ((active0 & 0x10000000000L) != 0L)
            return jjStopAtPos(1, 40);
         break;
      case 46:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000000000000L, active1, 0L);
      case 58:
         if ((active0 & 0x8000000000000L) != 0L)
            return jjStopAtPos(1, 51);
         break;
      case 61:
         if ((active0 & 0x200000000000000L) != 0L)
            return jjStopAtPos(1, 57);
         else if ((active0 & 0x400000000000000L) != 0L)
            return jjStopAtPos(1, 58);
         else if ((active0 & 0x800000000000000L) != 0L)
            return jjStopAtPos(1, 59);
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000000000L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L, active1, 0L);
      case 102:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(1, 17, 49);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0xc0000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x102400L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x100L, active1, 0L);
      case 124:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1){
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, 0L);
      return 2;
   }
   switch(curChar)
   {
      case 46:
         if ((active0 & 0x4000000000000000L) != 0L)
            return jjStopAtPos(2, 62);
         break;
      case 61:
         if ((active0 & 0x1000000000000000L) != 0L)
            return jjStopAtPos(2, 60);
         break;
      case 100:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(2, 13, 49);
         else if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(2, 15, 49);
         break;
      case 103:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x280000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x200L);
      case 114:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(2, 20, 49);
         break;
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
      case 116:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(2, 10, 49);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x100L);
      case 118:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(2, 12, 49);
         break;
      case 124:
         if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(2, 22);
         else if ((active0 & 0x800000L) != 0L)
            return jjStopAtPos(2, 23);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0, 0L);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, 0L);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(3, 8, 49);
         else if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(3, 18, 49);
         break;
      case 102:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(3, 19, 49);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x200L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, 0L);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, 0L);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(4, 9, 49);
         else if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(4, 21, 49);
         break;
      case 110:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(4, 14, 49);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0, 0L);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 48;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 15:
                  if (curChar == 42)
                     { jjCheckNAddTwoStates(21, 22); }
                  else if (curChar == 47)
                  {
                     if (kind > 5)
                        kind = 5;
                     { jjCheckNAddStates(0, 2); }
                  }
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                     { jjCheckNAddStates(3, 7); }
                  }
                  else if (curChar == 46)
                     { jjCheckNAddTwoStates(29, 33); }
                  else if (curChar == 47)
                     { jjAddStates(8, 9); }
                  else if (curChar == 39)
                     { jjCheckNAddTwoStates(10, 11); }
                  else if (curChar == 34)
                     { jjCheckNAddStates(10, 12); }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 29)
                        kind = 29;
                  }
                  break;
               case 48:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                     { jjCheckNAddTwoStates(34, 33); }
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                     { jjCheckNAddTwoStates(29, 30); }
                  }
                  break;
               case 49:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                     { jjCheckNAddTwoStates(34, 33); }
                  }
                  else if (curChar == 46)
                     { jjCheckNAdd(33); }
                  break;
               case 1:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     { jjCheckNAddStates(10, 12); }
                  break;
               case 3:
                  if ((0x8400000000L & l) != 0L)
                     { jjCheckNAddStates(10, 12); }
                  break;
               case 4:
                  if (curChar == 34 && kind > 25)
                     kind = 25;
                  break;
               case 5:
                  if ((0xff000000000000L & l) != 0L)
                     { jjCheckNAddStates(13, 16); }
                  break;
               case 6:
                  if ((0xff000000000000L & l) != 0L)
                     { jjCheckNAddStates(10, 12); }
                  break;
               case 7:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 8:
                  if ((0xff000000000000L & l) != 0L)
                     { jjCheckNAdd(6); }
                  break;
               case 9:
                  if (curChar == 39)
                     { jjCheckNAddTwoStates(10, 11); }
                  break;
               case 10:
                  if ((0xffffff7fffffffffL & l) != 0L)
                     { jjCheckNAddTwoStates(10, 11); }
                  break;
               case 11:
                  if (curChar == 39 && kind > 26)
                     kind = 26;
                  break;
               case 12:
                  if ((0x3ff000000000000L & l) != 0L && kind > 29)
                     kind = 29;
                  break;
               case 14:
                  if (curChar == 47)
                     { jjAddStates(8, 9); }
                  break;
               case 16:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  { jjCheckNAddStates(0, 2); }
                  break;
               case 17:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 18:
                  if (curChar == 10 && kind > 5)
                     kind = 5;
                  break;
               case 19:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 20:
                  if (curChar == 42)
                     { jjCheckNAddTwoStates(21, 22); }
                  break;
               case 21:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     { jjCheckNAddTwoStates(21, 22); }
                  break;
               case 22:
                  if (curChar == 42)
                     { jjCheckNAddStates(17, 19); }
                  break;
               case 23:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     { jjCheckNAddTwoStates(24, 22); }
                  break;
               case 24:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     { jjCheckNAddTwoStates(24, 22); }
                  break;
               case 25:
                  if (curChar == 47 && kind > 6)
                     kind = 6;
                  break;
               case 27:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 28:
                  if (curChar == 46)
                     { jjCheckNAddTwoStates(29, 33); }
                  break;
               case 29:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  { jjCheckNAddTwoStates(29, 30); }
                  break;
               case 31:
                  if ((0x280000000000L & l) != 0L)
                     { jjCheckNAdd(32); }
                  break;
               case 32:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  { jjCheckNAdd(32); }
                  break;
               case 33:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  { jjCheckNAddTwoStates(34, 33); }
                  break;
               case 34:
                  if (curChar == 46)
                     { jjCheckNAdd(33); }
                  break;
               case 37:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  { jjCheckNAddTwoStates(37, 38); }
                  break;
               case 38:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  { jjCheckNAdd(38); }
                  break;
               case 39:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  jjstateSet[jjnewStateCnt++] = 39;
                  break;
               case 40:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  { jjCheckNAddStates(3, 7); }
                  break;
               case 41:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  { jjCheckNAdd(41); }
                  break;
               case 42:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddTwoStates(42, 43); }
                  break;
               case 43:
                  if (curChar == 46)
                     { jjCheckNAdd(29); }
                  break;
               case 44:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddTwoStates(44, 45); }
                  break;
               case 46:
                  if ((0x280000000000L & l) != 0L)
                     { jjCheckNAdd(47); }
                  break;
               case 47:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 24)
                     kind = 24;
                  { jjCheckNAdd(47); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 29)
                        kind = 29;
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 30)
                        kind = 30;
                  }
                  else if (curChar == 95)
                  {
                     if (kind > 28)
                        kind = 28;
                     { jjCheckNAddTwoStates(37, 39); }
                  }
                  if ((0x7fffffe00000000L & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                     { jjCheckNAddTwoStates(34, 33); }
                  }
                  else if ((0x7fffffeL & l) != 0L)
                  {
                     if (kind > 7)
                        kind = 7;
                     { jjCheckNAdd(27); }
                  }
                  break;
               case 48:
               case 33:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  { jjCheckNAddTwoStates(34, 33); }
                  break;
               case 49:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  { jjCheckNAddTwoStates(34, 33); }
                  break;
               case 1:
                  if ((0xffffffffefffffffL & l) != 0L)
                     { jjCheckNAddStates(10, 12); }
                  break;
               case 2:
                  if (curChar == 92)
                     { jjAddStates(20, 22); }
                  break;
               case 3:
                  if ((0x14404410000000L & l) != 0L)
                     { jjCheckNAddStates(10, 12); }
                  break;
               case 10:
                  { jjAddStates(23, 24); }
                  break;
               case 12:
                  if ((0x7fffffe87fffffeL & l) != 0L && kind > 29)
                     kind = 29;
                  break;
               case 13:
                  if ((0x7fffffe07fffffeL & l) != 0L && kind > 30)
                     kind = 30;
                  break;
               case 16:
                  if (kind > 5)
                     kind = 5;
                  { jjAddStates(0, 2); }
                  break;
               case 21:
                  { jjCheckNAddTwoStates(21, 22); }
                  break;
               case 23:
               case 24:
                  { jjCheckNAddTwoStates(24, 22); }
                  break;
               case 26:
                  if ((0x7fffffeL & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  { jjCheckNAdd(27); }
                  break;
               case 27:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  { jjCheckNAdd(27); }
                  break;
               case 30:
                  if ((0x2000000020L & l) != 0L)
                     { jjAddStates(25, 26); }
                  break;
               case 35:
                  if ((0x7fffffe00000000L & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  { jjCheckNAddTwoStates(34, 33); }
                  break;
               case 36:
                  if (curChar != 95)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAddTwoStates(37, 39); }
                  break;
               case 38:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjstateSet[jjnewStateCnt++] = 38;
                  break;
               case 39:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAdd(39); }
                  break;
               case 45:
                  if ((0x2000000020L & l) != 0L)
                     { jjAddStates(27, 28); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjAddStates(10, 12); }
                  break;
               case 10:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjAddStates(23, 24); }
                  break;
               case 16:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 5)
                     kind = 5;
                  { jjAddStates(0, 2); }
                  break;
               case 21:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjCheckNAddTwoStates(21, 22); }
                  break;
               case 23:
               case 24:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjCheckNAddTwoStates(24, 22); }
                  break;
               default : if (i1 == 0 || l1 == 0 || i2 == 0 ||  l2 == 0) break; else break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 48 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   16, 17, 19, 41, 42, 43, 44, 45, 15, 20, 1, 2, 4, 1, 2, 6, 
   4, 22, 23, 25, 3, 5, 7, 10, 11, 31, 32, 46, 47, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, "\164\162\165\145", 
"\146\141\154\163\145", "\156\157\164", "\176", "\144\151\166", "\155\157\144", 
"\142\145\147\151\156", "\145\156\144", "\100", "\151\146", "\145\154\163\145", "\145\154\151\146", 
"\146\157\162", "\167\150\151\154\145", "\174\46\174", "\174\174\174", null, null, null, null, 
null, null, null, null, null, null, "\173", "\175", "\72\55", "\56", "\41", "\72", 
"\74\55", "\53", "\55", "\136", "\77", "\73", "\50", "\51", "\41\41", "\74", "\76", 
"\72\72", "\54", "\133", "\174", "\135", "\46", "\74\75", "\76\75", "\75\75", 
"\134\75\75", "\75", "\75\56\56", "\52", "\57", "\52\52", };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         TokenLexicalActions(matchedToken);
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      case 26 :
        image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
                  if (image.charAt(0) == '\'') matchedToken.image = image.substring(1, lengthOfMatch-1);
         break;
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public as2jTokenManager(SimpleCharStream stream){

      if (SimpleCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public as2jTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream)
  {
	
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 48; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit( SimpleCharStream stream, int lexState)
  {
  
    ReInit( stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xffffffffffffff81L, 0x3L, 
};
static final long[] jjtoSkip = {
   0x7eL, 0x0L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[48];
    private final int[] jjstateSet = new int[2 * 48];

    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    
    protected int curChar;
}
